import { useState, useRef, useEffect } from 'react';
import './BotonFlotante.css';

interface OpcionFab {
  etiqueta: string;
  accion: () => void;
}

interface BotonFlotanteProps {
  opciones: OpcionFab[];
}

export const BotonFlotante = ({ opciones }: BotonFlotanteProps) => {
  const [abierto, setAbierto] = useState(false);
  const contenedorRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickFuera = (event: MouseEvent) => {
      if (contenedorRef.current && !contenedorRef.current.contains(event.target as Node)) {
        setAbierto(false);
      }
    };

    if (abierto) {
      document.addEventListener('mousedown', handleClickFuera);
    }

    return () => {
      document.removeEventListener('mousedown', handleClickFuera);
    };
  }, [abierto]);

  return (
    <div className="fab-contenedor" ref={contenedorRef}>
      {abierto && (
        <div className="fab-opciones">
          {opciones.map((opcion, index) => (
            <button 
              key={index} 
              className="fab-opcion"
              onClick={() => {
                opcion.accion();
                setAbierto(false);
              }}
            >
              {opcion.etiqueta}
            </button>
          ))}
        </div>
      )}
      <button 
        className={`fab-principal ${abierto ? 'abierto' : ''}`}
        onClick={() => setAbierto(!abierto)}
      >
        +
      </button>
    </div>
  );
};