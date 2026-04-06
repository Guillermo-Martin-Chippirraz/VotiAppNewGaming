import type { EditarEventoDTO } from "../types/evento";
import type { CrearFormularioDTO, FormularioDTO } from "../types/formulario";
import api from "./api";

export const formularioService = {
    obtenerPorId: async (id: number): Promise<FormularioDTO> => {
        const res = await api.get<FormularioDTO>(`/formularios/${id}`);
        return res.data;
    },

    obtenerTodos: async (): Promise<FormularioDTO[]> => {
        const res = await api.get<FormularioDTO[]>("/formularios");
        return res.data;
    },

    crear: async (data: CrearFormularioDTO): Promise<FormularioDTO> => {
        const res = await api.post<FormularioDTO>("/formularios", data);
        return res.data;
    },

    obtenerPorEvento: async (idEvento: number): Promise<FormularioDTO> => {
        const res = await api.get<FormularioDTO>(`/formulario/evento/${idEvento}`);
        return res.data;
    },

    editar: async (id: number, data: EditarEventoDTO): Promise<FormularioDTO> => {
        const res = await api.put<FormularioDTO>(`/formularios/${id}`, data);
        return res.data;
    },

    eliminar: async (id: number): Promise<void> => {
        await api.delete(`/formularios/${id}`);
    },
};
