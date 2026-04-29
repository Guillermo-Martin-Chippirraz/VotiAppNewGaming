import './EtiquetaId.css';

interface EtiquetaIdProps {
  idObjeto?: string;
}

export const EtiquetaId = ({ idObjeto }: EtiquetaIdProps) => {
  if (!idObjeto) return null;

  return (
    <div className="etiqueta-id-contenedor">
      ID: {idObjeto}
    </div>
  );
};