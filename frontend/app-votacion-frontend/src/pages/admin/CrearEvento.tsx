import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ImagenUpload } from '../../components/ImagenUpload';
import { InputTexto } from '../../components/InputTexto';
import { SelectorFormulario } from '../../components/SelectorFormulario';
import { BannerUpload } from '../../components/BannerUpload';
import { SeccionCarrusel } from '../../components/SeccionCarrusel';
import { BotonFlotante } from '../../components/BotonFlotante';
import { BotonFlotanteAccion } from '../../components/BotonFlotanteAccion';
import { EtiquetaId } from '../../components/EtiquetaId';

export const CrearEvento = () => {
  const navigate = useNavigate();
  const [nombreEvento, setNombreEvento] = useState('');
  const [formulario, setFormulario] = useState('');
  
  const idGenerado = "EV-8X9P2Q";

  const formulariosPrueba = [
    "Formulario Estándar",
    "Formulario Avanzado",
    "Formulario Equipos"
  ];

  const opcionesCrear = [
    { etiqueta: 'jurado', accion: () => navigate('/admin/crear/jurado') },
    { etiqueta: 'participante', accion: () => navigate('/admin/crear/participante') }
  ];

  return (
    <div className="pantalla-movil-base">
      <h1 style={{
        fontSize: '28px',
        fontWeight: 'bold',
        margin: '16px 0 8px 0',
        textAlign: 'left',
        color: '#FFFFFF',
        fontFamily: '"Roboto", sans-serif',
        flexShrink: 0
      }}>
        CREAR EVENTO
      </h1>
      
      <div style={{
        minHeight: '2px',
        backgroundColor: '#4DE2C4',
        marginBottom: '24px',
        width: '100%',
        flexShrink: 0
      }} />

      <ImagenUpload onClick={() => {}} />

      <InputTexto 
        placeholder="NOMBRE DEL EVENTO" 
        valor={nombreEvento} 
        onChange={setNombreEvento} 
      />

      <SelectorFormulario 
        formularios={formulariosPrueba}
        valorSeleccionado={formulario}
        onSeleccionar={setFormulario}
      />

      <BannerUpload onClick={() => {}} />

      <SeccionCarrusel 
        titulo="JURADO" 
        items={[]} 
        onTituloClick={() => {}} 
        onItemClick={() => {}} 
      />

      <SeccionCarrusel 
        titulo="PARTICIPANTES" 
        items={[]} 
        onTituloClick={() => {}} 
        onItemClick={() => {}} 
      />

      <EtiquetaId idObjeto={idGenerado} />

      <BotonFlotanteAccion texto="CREAR" onClick={() => {}} />
      <BotonFlotante opciones={opcionesCrear} />
    </div>
  );
};