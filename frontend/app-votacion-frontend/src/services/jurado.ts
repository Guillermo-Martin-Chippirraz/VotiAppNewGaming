import type { EditarJuradoDTO, JuradoDTO } from "../types/jurado";
import type { VotacionDTO } from "../types/votacion";
import api from "./api";

export const juradoService = {
    obtenerTodos: async (): Promise<JuradoDTO[]> => {
        const res = await api.get<JuradoDTO[]>("/jurados");
        return res.data;
    },

    editarMisDatos: async (id: number, data: EditarJuradoDTO): Promise<JuradoDTO> => {
        const res = await api.put<JuradoDTO>(`/jurados/${id}`, data);
        return res.data;
    },

    retirarse: async (id: number): Promise<JuradoDTO> => {
        const res = await api.put<JuradoDTO>(`/jurados/${id}/retirarse`);
        return res.data;
    },

    eliminar: async (id: number): Promise<void> => {
        await api.delete(`/jurados/${id}`);
    },
    
    misVotaciones: async (): Promise<VotacionDTO[]> => {
        const res = await api.get<VotacionDTO[]>("/jurados/mis-votaciones");
        return res.data;
    },
};