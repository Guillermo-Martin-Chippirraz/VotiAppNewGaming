import type { EditarEventoDTO, EventoDTO } from "../types/evento";
import api from "./api";

export const eventoService = {
    obtenerTodos: async (): Promise<EventoDTO[]> => {
        const res = await api.get<EventoDTO[]>("/eventos");
        return res.data;
    },

    obtenerPorId: async (id: number): Promise<EventoDTO> => {
        const res = await api.get<EventoDTO>(`/eventos/${id}`);
        return res.data;
    },

    editar: async (id: number, data: EditarEventoDTO): Promise<EventoDTO> => {
        const res = await api.put<EventoDTO>(`/eventos/${id}`, data);
        return res.data;
    },

    crear: async (data: EditarEventoDTO): Promise<EventoDTO> => {
        const res = await api.post<EventoDTO>("/eventos", data);
        return res.data;
    },

    eliminar: async (id: number): Promise<void> => {
        await api.delete(`/eventos/${id}`);
    },

    habilitar: async (id: number): Promise<EventoDTO> => {
        const res = await api.put<EventoDTO>(`/eventos/${id}/habilitar`);
        return res.data;
    },

    deshabilitar: async (id: number): Promise<EventoDTO> => {
        const res = await api.put<EventoDTO>(`/eventos/${id}/deshabilitar`);
        return res.data;
    },
};


//Añadir métodos GET en back