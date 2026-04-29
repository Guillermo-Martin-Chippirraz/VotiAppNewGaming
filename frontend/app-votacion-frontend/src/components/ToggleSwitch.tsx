import './ToggleSwitch.css';

interface ToggleSwitchProps {
  opcion1: string;
  opcion2: string;
  seleccionada: string;
  onCambio: (opcion: string) => void;
}

export const ToggleSwitch = ({ opcion1, opcion2, seleccionada, onCambio }: ToggleSwitchProps) => {
  return (
    <div className="toggle-contenedor-ext">
      <div className="toggle-base">
        <button 
          className={`toggle-boton ${seleccionada === opcion1 ? 'activo' : ''}`}
          onClick={() => onCambio(opcion1)}
        >
          {opcion1}
        </button>
        <button 
          className={`toggle-boton ${seleccionada === opcion2 ? 'activo' : ''}`}
          onClick={() => onCambio(opcion2)}
        >
          {opcion2}
        </button>
      </div>
    </div>
  );
};