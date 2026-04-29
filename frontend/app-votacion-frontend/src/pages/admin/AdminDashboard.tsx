import { useNavigate } from 'react-router-dom';
import { TextoBienvenida } from '../../components/TextoBienvenida';
import { SeccionCarrusel } from '../../components/SeccionCarrusel';
import { BotonFlotante } from '../../components/BotonFlotante';

export const AdminDashboard = () => {
  const navigate = useNavigate();

  const eventosPrueba = [
    { id: 1, nombre: 'Liga Kanto', fecha: '2026-10-15' },
    { id: 2, nombre: 'Liga Johto', fecha: '2026-05-20' },
    { id: 3, nombre: 'Liga Hoenn', fecha: '2026-08-01' },
    { id: 4, nombre: 'Liga Sinnoh', fecha: '2026-11-10' },
    { id: 5, nombre: 'Liga Unnova', fecha: '2026-01-10' },
    { id: 6, nombre: 'Liga Kalos', fecha: '2026-03-12' },
    { id: 7, nombre: 'Liga Alola', fecha: '2026-07-22' }
  ];

  return (
    <div className="pantalla-movil-base">
      <TextoBienvenida />
      
      <SeccionCarrusel 
        titulo="EVENTOS" 
        items={eventosPrueba} 
        onTituloClick={() => navigate('/admin/lista/eventos')} 
        onItemClick={(id) => navigate(`/admin/eventos/${id}`)} 
      />
      
      <SeccionCarrusel 
        titulo="JURADO" 
        items={[]} 
        onTituloClick={() => navigate('/admin/lista/jurado')} 
        onItemClick={() => {}} 
      />

      <SeccionCarrusel 
        titulo="PARTICIPANTES" 
        items={[]} 
        onTituloClick={() => navigate('/admin/lista/participantes')} 
        onItemClick={() => {}} 
      />

      <BotonFlotante opciones={[
        { etiqueta: 'evento', accion: () => navigate('/admin/crear/evento') },
        { etiqueta: 'usuario', accion: () => navigate('/admin/crear/usuario') },
        { etiqueta: 'formulario', accion: () => navigate('/admin/crear/formulario') }
      ]} />
    </div>
  );
};