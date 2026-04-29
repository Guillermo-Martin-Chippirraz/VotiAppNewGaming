import { useState } from 'react';
import { InputTexto } from '../../components/InputTexto';
import { BotonFlotante } from '../../components/BotonFlotante';
import { BotonFlotanteAccion } from '../../components/BotonFlotanteAccion';

interface CampoValorar {
  id: number;
  label: string;
  valor: string;
}

export const CrearFormulario = () => {
  const [nombreFormulario, setNombreFormulario] = useState('');
  const [camposExtra, setCamposExtra] = useState<CampoValorar[]>([]);

  const añadirCampoExtra = () => {
    const nombreCampo = prompt("Introduce el nombre del campo a valorar:");
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
      <h1 style={{ 
        fontSize: '28px', 
        fontWeight: 'bold', 
        margin: '16px 0 8px 0', 
        textAlign: 'left', 
        color: '#FFFFFF', 
        fontFamily: '"Roboto", sans-serif', 
        flexShrink: 0 
      }}>
        CREAR FORMULARIO
      </h1>
      <div style={{ 
        minHeight: '2px', 
        backgroundColor: '#4DE2C4', 
        marginBottom: '24px', 
        width: '100%', 
        flexShrink: 0 
      }} />

      <InputTexto 
        placeholder="NOMBRE DEL FORMULARIO" 
        valor={nombreFormulario} 
        onChange={setNombreFormulario} 
      />

      <InputTexto 
        placeholder="CAMPO A VALORAR" 
        valor="" 
        onChange={() => {}} 
      />

      {camposExtra.map(campo => (
        <InputTexto 
          key={campo.id}
          placeholder={campo.label}
          valor={campo.valor}
          onChange={(v) => actualizarCampoExtra(campo.id, v)}
        />
      ))}

      <BotonFlotanteAccion texto="CREAR" onClick={() => {}} />
      
      <BotonFlotante opciones={[
        { etiqueta: 'campo extra', accion: añadirCampoExtra }
      ]} />
    </div>
  );
};