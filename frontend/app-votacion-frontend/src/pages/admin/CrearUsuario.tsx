import { useState } from 'react';
import { ImagenUpload } from '../../components/ImagenUpload';
import { InputTexto } from '../../components/InputTexto';
import { ToggleSwitch } from '../../components/ToggleSwitch';
import { EtiquetaId } from '../../components/EtiquetaId';
import { BotonFlotante } from '../../components/BotonFlotante';
import { BotonFlotanteAccion } from '../../components/BotonFlotanteAccion';

interface CampoExtra {
  id: number;
  label: string;
  valor: string;
}

export const CrearUsuario = () => {
  const [tipoUsuario, setTipoUsuario] = useState('JURADO');
  const [tipoParticipante, setTipoParticipante] = useState('PARTICUL.');
  const [nombre, setNombre] = useState('');
  const [pass, setPass] = useState('');
  const [passRep, setPassRep] = useState('');
  const [camposExtra, setCamposExtra] = useState<CampoExtra[]>([]);

  const idGenerado = "USR-44210";

  const añadirCampoExtra = () => {
    const nombreCampo = prompt("Introduce el nombre del campo extra:");
    if (nombreCampo) {
      setCamposExtra([...camposExtra, {
        id: Date.now(),
        label: nombreCampo.toUpperCase(),
        valor: ''
      }]);
    }
  };

  const actualizarCampoExtra = (id: number, nuevoValor: string) => {
    setCamposExtra(camposExtra.map(c => c.id === id ? { ...c, valor: nuevoValor } : c));
  };

  return (
    <div className="pantalla-movil-base">
      <h1 style={{ fontSize: '28px', fontWeight: 'bold', margin: '16px 0 8px 0', textAlign: 'left', color: '#FFFFFF', fontFamily: '"Roboto", sans-serif', flexShrink: 0 }}>
        CREAR USUARIO
      </h1>
      <div style={{ minHeight: '2px', backgroundColor: '#4DE2C4', marginBottom: '24px', width: '100%', flexShrink: 0 }} />

      <ImagenUpload onClick={() => {}} />

      <InputTexto 
        placeholder="NOMBRE DEL USUARIO" 
        valor={nombre} 
        onChange={setNombre} 
      />

      <ToggleSwitch 
        opcion1="JURADO" 
        opcion2="PARTICIP." 
        seleccionada={tipoUsuario} 
        onCambio={setTipoUsuario} 
      />

      {tipoUsuario === 'JURADO' ? (
        <>
          <InputTexto placeholder="CONTRASEÑA" valor={pass} onChange={setPass} tipo="password" />
          <InputTexto placeholder="REPETIR CONTRASEÑA" valor={passRep} onChange={setPassRep} tipo="password" />
        </>
      ) : (
        <ToggleSwitch 
          opcion1="EMPRESA" 
          opcion2="PARTICUL." 
          seleccionada={tipoParticipante} 
          onCambio={setTipoParticipante} 
        />
      )}

      {camposExtra.map(campo => (
        <InputTexto 
          key={campo.id}
          placeholder={campo.label}
          valor={campo.valor}
          onChange={(v) => actualizarCampoExtra(campo.id, v)}
        />
      ))}

      <EtiquetaId idObjeto={idGenerado} />

      <BotonFlotanteAccion texto="CREAR" onClick={() => {}} />
      
      <BotonFlotante opciones={[
        { etiqueta: 'campo extra', accion: añadirCampoExtra }
      ]} />
    </div>
  );
};