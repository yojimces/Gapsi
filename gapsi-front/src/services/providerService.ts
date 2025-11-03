import api from './api'
import type { Provider, PagedResponse, ProviderFormData } from '../types/provider'

export const getProviders = async (page = 0, size = 10): Promise<PagedResponse<Provider>> => {
  const response = await api.get<PagedResponse<Provider>>('/proveedores', {
    params: { page, size }
  })
  return response.data
}

export const createProvider = async (providerData: ProviderFormData): Promise<Provider> => {
  const response = await api.post<Provider>('/proveedores', providerData)
  return response.data
}

export const deleteProvider = async (id: number): Promise<void> => {
  await api.delete(`/proveedores/${id}`)
}
