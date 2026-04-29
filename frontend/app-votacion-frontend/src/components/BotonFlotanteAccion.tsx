import './BotonFlotanteAccion.css';

interface BotonFlotanteAccionProps {
  texto: string;
  onClick: () => void;
}

export const BotonFlotanteAccion = ({ texto, onClick }: BotonFlotanteAccionProps) => {
  return (
    <button className="fab-accion-izq" onClick={onClick}>
      {texto}
    </button>
  );
};