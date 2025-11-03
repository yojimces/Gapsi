import { createSlice } from '@reduxjs/toolkit'

interface AppState {
  isLoading: boolean
  error: string | null
}

const initialState: AppState = {
  isLoading: false,
  error: null
}

export const appSlice = createSlice({
  name: 'app',
  initialState,
  reducers: {
    setLoading: (state, action) => {
      state.isLoading = action.payload
    },
    setError: (state, action) => {
      state.error = action.payload
    },
    clearError: (state) => {
      state.error = null
    }
  }
})

export const { setLoading, setError, clearError } = appSlice.actions
export default appSlice.reducer
