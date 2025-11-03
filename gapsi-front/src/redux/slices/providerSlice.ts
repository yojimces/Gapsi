import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import type { PayloadAction } from '@reduxjs/toolkit'
import * as providerService from '../../services/providerService'
import type { Provider, PagedResponse, ProviderFormData } from '../../types/provider'

interface ProviderState {
  items: Provider[]
  loading: boolean
  error: string | null
  currentPage: number
  totalPages: number
  totalElements: number
}

const initialState: ProviderState = {
  items: [],
  loading: false,
  error: null,
  currentPage: 0,
  totalPages: 0,
  totalElements: 0
}

// Async thunks
export const fetchProviders = createAsyncThunk<
  PagedResponse<Provider>,
  { page?: number; size?: number }
>(
  'providers/fetchProviders',
  async ({ page = 0, size = 10 }) => {
    const response = await providerService.getProviders(page, size)
    return response
  }
)

export const addProvider = createAsyncThunk<
  Provider,
  ProviderFormData,
  { rejectValue: string }
>(
  'providers/addProvider',
  async (providerData, { rejectWithValue }) => {
    try {
      const response = await providerService.createProvider(providerData)
      return response
    } catch (error: any) {
      return rejectWithValue(error.response?.data?.message || 'Error al agregar')
    }
  }
)

export const deleteProvider = createAsyncThunk<number, number>(
  'providers/deleteProvider',
  async (id) => {
    await providerService.deleteProvider(id)
    return id
  }
)

const providerSlice = createSlice({
  name: 'providers',
  initialState,
  reducers: {
    clearError: (state) => {
      state.error = null
    }
  },
  extraReducers: (builder) => {
    builder
      // Fetch providers
      .addCase(fetchProviders.pending, (state) => {
        state.loading = true
      })
      .addCase(fetchProviders.fulfilled, (state, action: PayloadAction<PagedResponse<Provider>>) => {
        state.loading = false
        state.items = [...state.items, ...action.payload.content]
        state.currentPage = action.payload.number
        state.totalPages = action.payload.totalPages
        state.totalElements = action.payload.totalElements
      })
      .addCase(fetchProviders.rejected, (state, action) => {
        state.loading = false
        state.error = action.error.message || 'Error al cargar proveedores'
      })
      
      // Add provider
      .addCase(addProvider.fulfilled, (state, action: PayloadAction<Provider>) => {
        state.items.unshift(action.payload)
      })
      
      // Delete provider
      .addCase(deleteProvider.fulfilled, (state, action: PayloadAction<number>) => {
        state.items = state.items.filter(item => item.id !== action.payload)
      })
  }
})

export const { clearError } = providerSlice.actions
export default providerSlice.reducer
