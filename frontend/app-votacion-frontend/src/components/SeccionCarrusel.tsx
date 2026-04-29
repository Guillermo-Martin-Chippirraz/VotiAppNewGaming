import { useState, useRef, useEffect } from 'react';
import { ItemCarrusel } from './ItemCarrusel';
import flechaImg from '../assets/images/FLECHA.webp';
import './SeccionCarrusel.css';

interface SeccionCarruselProps {
  titulo: string;
  items: Array<{ id: string | number; nombre: string; imagen?: string }>;
  onTituloClick: () => void;
  onItemClick: (id: string | number) => void;
}

export const SeccionCarrusel = ({ titulo, items, onTituloClick, onItemClick }: SeccionCarruselProps) => {
  const [hayOverflow, setHayOverflow] = useState(false);
  const carruselRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const comprobarOverflow = () => {
      if (carruselRef.current) {
        const { scrollWidth, clientWidth } = carruselRef.current;
        setHayOverflow(scrollWidth > clientWidth);
      }
    };

    comprobarOverflow();
    window.addEventListener('resize', comprobarOverflow);
    return () => window.removeEventListener('resize', comprobarOverflow);
  }, [items]);

  return (
    <div className="seccion-frame">
      <h2 className="titulo-seccion" onClick={onTituloClick}>{titulo}</h2>
      <hr className="separador-celeste" />
      
      <div className="carrusel-contenedor" ref={carruselRef}>
        {items.map(item => (
          <ItemCarrusel
            key={item.id}
            nombre={item.nombre}
            imagen={item.imagen}
            onClick={() => onItemClick(item.id)}
          />
        ))}
      </div>

      {hayOverflow && (
        <div className="contenedor-flecha">
          <img src={flechaImg} alt="Ver más" />
        </div>
      )}
    </div>
  );
};