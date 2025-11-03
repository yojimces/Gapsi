import { configureStore } from '@reduxjs/toolkit'
import providerReducer from './slices/providerSlice'
import appReducer from './slices/appSlice'
import loggerMiddleware from './middleware/logger'

export const store = configureStore({
  reducer: {
    providers: providerReducer,
    app: appReducer
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(loggerMiddleware)
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch

