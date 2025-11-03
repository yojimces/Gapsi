import React from 'react'
import { useDispatch } from 'react-redux'
import { TableRow, TableCell, IconButton, Tooltip, Chip } from '@mui/material'
import { 
  Delete as DeleteIcon, 
  Visibility as VisibilityIcon 
} from '@mui/icons-material'
import { deleteProvider } from '../../redux/slices/providerSlice'
import type { AppDispatch } from '../../redux/store'
import type { Provider } from '../../types/provider'

interface ProviderItemProps {
  provider: Provider
}

const ProviderItem: React.FC<ProviderItemProps> = ({ provider }) => {
  const dispatch = useDispatch<AppDispatch>()

  const handleDelete = async () => {
    if (window.confirm('¿Está seguro de eliminar este proveedor?')) {
      await dispatch(deleteProvider(provider.id!))
    }
  }

  // Determinar estado basado en algún criterio (por ahora aleatorio para demo)
  const getStatusColor = () => {
    const colors = ['success', 'error', 'warning']
    return colors[provider.id! % 3] as 'success' | 'error' | 'warning'
  }

  const getStatusDot = (color: 'success' | 'error' | 'warning') => {
    const colors = {
      success: '#4caf50',
      error: '#f44336',
      warning: '#ff9800'
    }
    return (
      <span 
        className="status-dot" 
        style={{ 
          display: 'inline-block',
          width: '12px', 
          height: '12px', 
          borderRadius: '50%', 
          backgroundColor: colors[color]
        }}
      />
    )
  }

  const activeColor = getStatusColor()

  return (
    <TableRow hover className="provider-table-row">
      <TableCell>{getStatusDot(activeColor)}</TableCell>
      <TableCell>{provider.nombre}</TableCell>
      <TableCell>{provider.razonSocial}</TableCell>
      <TableCell>
        <Chip 
          label="Si" 
          color={activeColor} 
          size="small"
          variant="outlined"
        />
      </TableCell>
      <TableCell>{provider.direccion}</TableCell>
      <TableCell align="right">
        $ {((provider.id || 0) * 1200).toLocaleString('es-MX')} MXN
      </TableCell>
      <TableCell align="center">
        <Tooltip title="Ver detalles">
          <IconButton size="small" color="primary">
            <VisibilityIcon fontSize="small" />
          </IconButton>
        </Tooltip>
        <Tooltip title="Eliminar">
          <IconButton 
            size="small" 
            color="error" 
            onClick={handleDelete}
          >
            <DeleteIcon fontSize="small" />
          </IconButton>
        </Tooltip>
      </TableCell>
    </TableRow>
  )
}

export default ProviderItem
