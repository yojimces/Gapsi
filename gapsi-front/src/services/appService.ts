import api from './api'
import type { WelcomeData } from '../types/api'

export const getWelcomeData = async (): Promise<WelcomeData> => {
  const response = await api.get<WelcomeData>('/app/welcome')
  return response.data
}
