import React from 'react';

interface LoadingProps {
  message?: string;
}

export const Loading: React.FC<LoadingProps> = ({
  message = 'Cargando...',
}) => {
  return (
    <div style={{ textAlign: 'center', padding: '2rem' }}>
      <div style={{ marginBottom: '1rem' }}>⏳</div>
      <p>{message}</p>
    </div>
  );
};

