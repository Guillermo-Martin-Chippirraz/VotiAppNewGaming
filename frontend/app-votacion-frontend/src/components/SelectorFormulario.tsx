import { useState, useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './SelectorFormulario.css';

interface SelectorFormularioProps {
  formularios: string[];
  valorSeleccionado: string;
  onSeleccionar: (valor: string) => void;
}

export const SelectorFormulario = ({ formularios, valorSeleccionado, onSeleccionar }: SelectorFormularioProps) => {
  const [abierto, setAbierto] = useState(false);
  const contenedorRef = useRef<HTMLDivElement>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const handleClickFuera = (event: MouseEvent) => {
      if (contenedorRef.current && !contenedorRef.current.contains(event.target as Node)) {
        setAbierto(false);
      }
    };

    if (abierto) {
      document.addEventListener('mousedown', handleClickFuera);
    }
    return () => document.removeEventListener('mousedown', handleClickFuera);
  }, [abierto]);

  return (
    <div className="selector-contenedor" ref={contenedorRef}>
      <button 
        className="selector-trigger" 
        onClick={() => setAbierto(!abierto)}
      >
        {valorSeleccionado || "SELECCIONAR FORMULARIO"}
      </button>

      {abierto && (
        <div className="selector-menu">
          {formularios.map((form, index) => (
            <div 
              key={index} 
              className="selector-item"
              onClick={() => {
                onSeleccionar(form);
                setAbierto(false);
              }}
            >
              {form}
            </div>
          ))}
          <div 
            className="selector-item item-crear"
            onClick={() => {
              setAbierto(false);
              navigate('/admin/crear/formulario');
            }}
          >
            CREAR FORMULARIO
          </div>
        </div>
      )}
    </div>
  );
};