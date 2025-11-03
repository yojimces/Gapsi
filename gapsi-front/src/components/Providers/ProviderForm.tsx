import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { 
  Dialog, 
  DialogTitle, 
  DialogContent, 
  DialogActions,
  TextField, 
  Button,
  Alert
} from '@mui/material'
import { addProvider } from '../../redux/slices/providerSlice'
import type { AppDispatch } from '../../redux/store'
import type { ProviderFormData } from '../../types/provider'
import { validateProviderForm } from '../../utils/validators'

interface ProviderFormProps {
  open: boolean
  onClose: () => void
}

const ProviderForm: React.FC<ProviderFormProps> = ({ open, onClose }) => {
  const dispatch = useDispatch<AppDispatch>()
  const [formData, setFormData] = useState<ProviderFormData>({
    nombre: '',
    razonSocial: '',
    direccion: ''
  })
  const [errors, setErrors] = useState<Partial<Record<keyof ProviderFormData, string>>>({})
  const [submitError, setSubmitError] = useState<string | null>(null)

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setFormData(prev => ({ ...prev, [name]: value }))
    // Limpiar error del campo al escribir
    if (errors[name as keyof ProviderFormData]) {
      setErrors(prev => ({ ...prev, [name]: '' }))
    }
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    
    // Validar formulario
    const validationErrors = validateProviderForm(formData)
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors)
      return
    }

    try {
      await dispatch(addProvider(formData)).unwrap()
      setFormData({ nombre: '', razonSocial: '', direccion: '' })
      setSubmitError(null)
      onClose()
    } catch (error: any) {
      // Error de duplicado viene del backend
      setSubmitError(error.message || 'Error al agregar proveedor')
    }
  }

  return (
    <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
      <DialogTitle>
        <i className="fas fa-plus-circle"></i> Agregar Proveedor
      </DialogTitle>
      
      <form onSubmit={handleSubmit}>
        <DialogContent>
          {submitError && (
            <Alert severity="error" sx={{ mb: 2 }}>
              {submitError}
            </Alert>
          )}
          
          <TextField
            fullWidth
            label="Nombre"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            error={!!errors.nombre}
            helperText={errors.nombre}
            margin="normal"
            required
          />
          
          <TextField
            fullWidth
            label="Razón Social"
            name="razonSocial"
            value={formData.razonSocial}
            onChange={handleChange}
            error={!!errors.razonSocial}
            helperText={errors.razonSocial}
            margin="normal"
            required
          />
          
          <TextField
            fullWidth
            label="Dirección"
            name="direccion"
            value={formData.direccion}
            onChange={handleChange}
            error={!!errors.direccion}
            helperText={errors.direccion}
            margin="normal"
            required
            multiline
            rows={3}
          />
        </DialogContent>
        
        <DialogActions>
          <Button onClick={onClose}>Cancelar</Button>
          <Button type="submit" variant="contained" color="primary">
            Guardar
          </Button>
        </DialogActions>
      </form>
    </Dialog>
  )
}

export default ProviderForm
