export interface Provider {
  id?: number
  nombre: string
  razonSocial: string
  direccion: string
  createdAt?: string
  updatedAt?: string
}

export interface ProviderFormData {
  nombre: string
  razonSocial: string
  direccion: string
}

export interface PagedResponse<T> {
  content: T[]
  number: number
  size: number
  totalElements: number
  totalPages: number
  last: boolean
  first: boolean
}

