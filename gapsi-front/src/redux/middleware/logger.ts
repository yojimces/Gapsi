import type { Middleware } from '@reduxjs/toolkit'

const logger: Middleware = (store) => (next) => (action) => {
  if (import.meta.env.DEV) {
    console.log('Dispatching action:', action)
    console.log('State before:', store.getState())
  }
  
  const result = next(action)
  
  if (import.meta.env.DEV) {
    console.log('State after:', store.getState())
  }
  
  return result
}

export default logger
