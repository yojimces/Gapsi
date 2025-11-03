import type { ProviderFormData } from '../types/provider'

export const validateProviderForm = (
  formData: ProviderFormData
): Partial<Record<keyof ProviderFormData, string>> => {
  const errors: Partial<Record<keyof ProviderFormData, string>> = {}

  if (!formData.nombre || formData.nombre.trim().length === 0) {
    errors.nombre = 'El nombre es requerido'
  } else if (formData.nombre.length < 3) {
    errors.nombre = 'El nombre debe tener al menos 3 caracteres'
  }

  if (!formData.razonSocial || formData.razonSocial.trim().length === 0) {
    errors.razonSocial = 'La razón social es requerida'
  } else if (formData.razonSocial.length < 3) {
    errors.razonSocial = 'La razón social debe tener al menos 3 caracteres'
  }

  if (!formData.direccion || formData.direccion.trim().length === 0) {
    errors.direccion = 'La dirección es requerida'
  } else if (formData.direccion.length < 10) {
    errors.direccion = 'La dirección debe tener al menos 10 caracteres'
  }

  return errors
}
