// PATRÓN DE DISEÑO: Observer
// Implementación del patrón Observer para sistema de notificaciones
// Permite que múltiples componentes se suscriban a eventos del sistema

type NotificationType = 'success' | 'error' | 'info' | 'warning'

interface Notification {
  type: NotificationType
  message: string
  timestamp: number
}

type ObserverCallback = (notification: Notification) => void

class NotificationObserver {
  private static instance: NotificationObserver
  private observers: ObserverCallback[] = []

  private constructor() {}

  public static getInstance(): NotificationObserver {
    if (!NotificationObserver.instance) {
      NotificationObserver.instance = new NotificationObserver()
    }
    return NotificationObserver.instance
  }

  public subscribe(observer: ObserverCallback): () => void {
    this.observers.push(observer)
    return () => {
      this.observers = this.observers.filter(obs => obs !== observer)
    }
  }

  public notify(type: NotificationType, message: string): void {
    const notification: Notification = {
      type,
      message,
      timestamp: Date.now()
    }
    this.observers.forEach(observer => {
      observer(notification)
    })
  }

  public success(message: string): void {
    this.notify('success', message)
  }

  public error(message: string): void {
    this.notify('error', message)
  }

  public info(message: string): void {
    this.notify('info', message)
  }

  public warning(message: string): void {
    this.notify('warning', message)
  }
}

export default NotificationObserver.getInstance()
