import './ImagenUpload.css';
import iconoPlaceholder from '../assets/images/EDIT.webp';

interface ImagenUploadProps {
  onClick: () => void;
}

export const ImagenUpload = ({ onClick }: ImagenUploadProps) => {
  return (
    <div className="imagen-upload-contenedor" onClick={onClick}>
      <img src={iconoPlaceholder} alt="Subir imagen" />
    </div>
  );
};