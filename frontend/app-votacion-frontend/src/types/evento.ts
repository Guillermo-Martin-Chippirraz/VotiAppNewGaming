export interface EditarEventoDTO {
    nombre?: string;
    descripcion?: string;
    fecha?: string; // ISO date string (LocalDate)
    estado?: string;
    bannerUrl?: string; // añadir en back
}

export interface EventoDTO {
    id: number;
    nombre: string;
    descripcion: string;
    estado: string;
    fecha: string; // ISO date string
    idUsuarioCreador: number;
    creadoEn: string; // ISO datetime
    actualizadoEn: string; // ISO datetime
    bannerUrl?: string; // añadir en back
}