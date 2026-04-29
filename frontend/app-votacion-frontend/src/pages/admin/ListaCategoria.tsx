import { useState, useMemo, useEffect, useRef } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ItemCarrusel } from '../../components/ItemCarrusel';
import iconoFiltro from '../../assets/images/EDIT.webp';
import './ListaCategoria.css';

const basesDeDatosSimuladas: Record<string, Array<{ id: number; nombre: string; fecha: string }>> = {
  eventos: [
    { id: 1, nombre: 'Liga Kanto', fecha: '2026-10-15' },
    { id: 2, nombre: 'Liga Johto', fecha: '2026-05-20' },
    { id: 3, nombre: 'Liga Hoenn', fecha: '2026-08-01' },
    { id: 4, nombre: 'Liga Sinnoh', fecha: '2026-11-10' },
    { id: 5, nombre: 'Liga Unnova', fecha: '2026-01-10' },
    { id: 6, nombre: 'Liga Kalos', fecha: '2026-03-12' },
    { id: 7, nombre: 'Liga Alola', fecha: '2026-07-22' }
  ],
  jurado: [
    { id: 1, nombre: 'Rojo', fecha: '2026-01-15' },
    { id: 2, nombre: 'Azul', fecha: '2026-02-10' },
    { id: 3, nombre: 'Ash Ketchum', fecha: '2026-03-05' },
    { id: 4, nombre: 'Misty', fecha: '2026-04-20' }
  ],
  participantes: [
    { id: 1, nombre: 'Team Rocket', fecha: '2026-05-01' },
    { id: 2, nombre: 'Team Magma', fecha: '2026-06-15' },
    { id: 3, nombre: 'Team Aqua', fecha: '2026-07-10' },
    { id: 4, nombre: 'Team Galaxy', fecha: '2026-08-25' },
    { id: 5, nombre: 'Team Plasma', fecha: '2026-09-30' }
  ]
};

export const ListaCategoria = () => {
  const { categoria } = useParams<{ categoria: string }>();
  const navigate = useNavigate();
  const [mostrarFiltro, setMostrarFiltro] = useState(false);
  const [orden, setOrden] = useState<'alfabetico' | 'fecha'>('fecha');
  const filtroRef = useRef<HTMLDivElement>(null);

  const titulo = categoria ? categoria.toUpperCase() : 'EVENTOS';

  const itemsOrdenados = useMemo(() => {
    const items = categoria && basesDeDatosSimuladas[categoria] 
      ? basesDeDatosSimuladas[categoria] 
      : [];
      
    return [...items].sort((a, b) => {
      if (orden === 'alfabetico') return a.nombre.localeCompare(b.nombre);
      return new Date(a.fecha).getTime() - new Date(b.fecha).getTime();
    });
  }, [categoria, orden]);

  useEffect(() => {
    const handleClickFuera = (event: MouseEvent) => {
      if (filtroRef.current && !filtroRef.current.contains(event.target as Node)) {
        setMostrarFiltro(false);
      }
    };

    if (mostrarFiltro) {
      document.addEventListener('mousedown', handleClickFuera);
    }
    return () => document.removeEventListener('mousedown', handleClickFuera);
  }, [mostrarFiltro]);

  return (
    <div className="pantalla-movil-base">
      <div className="cabecera-lista">
        <h1 className="titulo-seccion">{titulo}</h1>
        <div className="contenedor-filtro" ref={filtroRef}>
          <button 
            className="btn-filtro" 
            onClick={() => setMostrarFiltro(!mostrarFiltro)}
          >
            <img src={iconoFiltro} alt="Filtrar" />
          </button>
          
          {mostrarFiltro && (
            <div className="menu-filtro">
              <button onClick={() => { setOrden('alfabetico'); setMostrarFiltro(false); }}>
                Alfabético
              </button>
              <button onClick={() => { setOrden('fecha'); setMostrarFiltro(false); }}>
                Fecha
              </button>
            </div>
          )}
        </div>
      </div>
      <hr className="separador-celeste" />

      <div className="grid-categorias">
        {itemsOrdenados.map(item => (
          <ItemCarrusel
            key={item.id}
            nombre={item.nombre}
            onClick={() => navigate(`/admin/${categoria}/${item.id}`)}
          />
        ))}
      </div>
    </div>
  );
};