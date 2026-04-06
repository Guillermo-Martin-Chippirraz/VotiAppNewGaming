import type { CrearVotacionDTO, EditarVotacionDTO, VotacionDTO } from "../types/votacion";
import api from "./api";

export const votacionService = {
    obtenerPorId: async (id: number): Promise<VotacionDTO> => {
        const res = await api.get<VotacionDTO>(`/votaciones/${id}`);
        return res.data;
    },

    obtenerTodas: async (): Promise<VotacionDTO[]> => {
        const res = await api.get<VotacionDTO[]>("/votaciones/");
        return res.data;
    },

    obtenerPorEvento: async (idEvento: number): Promise<VotacionDTO[]> => {
        const res = await api.get<VotacionDTO[]>(`/votaciones/evento/${idEvento}`);
        return res.data;
    },

    obtenerPorEmpresa: async (idEmpresa: number): Promise<VotacionDTO[]> => {
        const res = await api.get<VotacionDTO[]>(`/votaciones/empresa/${idEmpresa}`);
        return res.data;
    },

    obtenerPorJurado: async (idJurado: number): Promise<VotacionDTO[]> => {
        const res = await api.get<VotacionDTO[]>(`/votaciones/jurado/${idJurado}`);
        return res.data;
    },

    crear: async (data: CrearVotacionDTO): Promise<VotacionDTO> => {
        const res = await api.post<VotacionDTO>("/votaciones", data);
        return res.data;
    },

    editar: async (id: number, data: EditarVotacionDTO): Promise<VotacionDTO> => {
        const res = await api.put<VotacionDTO>(`/votacion/${id}`, data);
        return res.data;
    },

    eliminar: async (idVotacion: number): Promise<void> => {
        await api.delete(`/votaciones/${idVotacion}`);
    },
};