import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { AuthProvider } from './context/AuthContext.tsx'
import { EventoProvider } from './context/EventoContext.tsx'
import { EmpresaProvider } from './context/EmpresaContext.tsx'
import { FormularioProvider } from './context/FormularioContext.tsx'
import { JuradoProvider } from './context/JuradoContext.tsx'
import { VotacionProvider } from './context/VotacionContext.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <AuthProvider>
        <EventoProvider>
          <EmpresaProvider>
            <FormularioProvider>
              <JuradoProvider>
                <VotacionProvider>
                  <App />
                </VotacionProvider>
              </JuradoProvider>
            </FormularioProvider>
          </EmpresaProvider>
        </EventoProvider>
    </AuthProvider>
  </StrictMode>,
)
