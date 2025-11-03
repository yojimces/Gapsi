import { useState } from 'react'
import { Container, Fab, Button, Box } from '@mui/material'
import { Add as AddIcon, Print as PrintIcon, ArrowBack as ArrowBackIcon } from '@mui/icons-material'
import Header from './components/Header/Header'
import WelcomePage from './components/Welcome/WelcomePage'
import ProviderList from './components/Providers/ProviderList'
import ProviderForm from './components/Providers/ProviderForm'
import './App.css'

function App() {
  const [showProviderForm, setShowProviderForm] = useState(false)
  const [showProviders, setShowProviders] = useState(false)

  const handlePrint = () => {
    window.print()
  }

  return (
    <div className="App">
      <Header />
      {!showProviders ? (
        <WelcomePage onNavigateToProviders={() => setShowProviders(true)} />
      ) : (
        <Container sx={{ py: 4 }}>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
            <Button
              variant="outlined"
              startIcon={<ArrowBackIcon />}
              onClick={() => setShowProviders(false)}
            >
              Volver
            </Button>
            <Box sx={{ display: 'flex', gap: 2 }}>
              <Button
                variant="contained"
                startIcon={<AddIcon />}
                onClick={() => setShowProviderForm(true)}
              >
                Agregar elemento
              </Button>
              <Button
                variant="contained"
                color="secondary"
                startIcon={<PrintIcon />}
                onClick={handlePrint}
              >
                Imprimir elemento
              </Button>
            </Box>
          </Box>
          <ProviderList />
        </Container>
      )}
      
      {showProviders && (
        <Fab
          color="primary"
          aria-label="add"
          onClick={() => setShowProviderForm(true)}
          sx={{
            position: 'fixed',
            bottom: 24,
            right: 24,
          }}
        >
          <AddIcon />
        </Fab>
      )}

      <ProviderForm
        open={showProviderForm}
        onClose={() => setShowProviderForm(false)}
      />
    </div>
  )
}

export default App
