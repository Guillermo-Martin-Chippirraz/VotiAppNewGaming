import './BannerUpload.css';
import iconoPlaceholder from '../assets/images/EDIT.webp';

interface BannerUploadProps {
  onClick: () => void;
}

export const BannerUpload = ({ onClick }: BannerUploadProps) => {
  return (
    <div className="banner-upload-contenedor">
      <div className="banner-upload-area" onClick={onClick}>
        <img src={iconoPlaceholder} alt="Subir banner" />
      </div>
    </div>
  );
};