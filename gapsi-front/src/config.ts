interface Config {
  apiBaseUrl: string
  appVersion: string
  pageSize: number
}

export const config: Config = {
  apiBaseUrl: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
  appVersion: import.meta.env.VITE_APP_VERSION || '1.0.0',
  pageSize: parseInt(import.meta.env.VITE_PAGE_SIZE || '10', 10)
}

