import type { EventoDTO } from "./evento";

export interface EditarFormularioDTO {
    listaDePreguntas?: string; // JSON string
}

export interface FormularioDTO {
    idFormulario: number;
    evento: EventoDTO;
    listaDePreguntas: string; // JSON string
    creadoEn: string; // ISO datetime
    actualizadoEn: string; // ISO datetime
}

export interface CrearFormularioDTO {
    idEvento: number;
    listaDePreguntas: string;
}