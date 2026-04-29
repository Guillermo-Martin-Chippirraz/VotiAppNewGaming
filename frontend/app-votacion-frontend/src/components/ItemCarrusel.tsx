import './ItemCarrusel.css';
import imagenPlaceholder from '../assets/images/IMAGE.webp';

interface ItemCarruselProps {
  nombre: string;
  imagen?: string;
  onClick: () => void;
}

export const ItemCarrusel = ({ nombre, imagen, onClick }: ItemCarruselProps) => {
  return (
    <div className="item-carrusel" onClick={onClick}>
      <div className="imagen-contenedor">
        <img src={imagen || imagenPlaceholder} alt={nombre} />
      </div>
      <span className="nombre-item">{nombre}</span>
    </div>
  );
};