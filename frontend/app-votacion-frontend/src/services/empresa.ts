import type { EditarEmpresaDTO, EmpresaDTO } from "../types/empresa";
import api from "./api";

export const empresaService = {
    obtenerTodos: async (): Promise<EmpresaDTO[]> => {
        const res = await api.get<EmpresaDTO[]>("/empresas");
        return res.data;
    },

    obtenerPorId: async (id: number): Promise<EmpresaDTO> => {
        const res = await api.get<EmpresaDTO>(`/empresas/${id}`);
        return res.data;
    },

    crear: async (data: EmpresaDTO): Promise<EmpresaDTO> => {
        const res = await api.post<EmpresaDTO>("/empresas", data);
        return res.data;
    },

    editar: async (idEmpresa: number, data: EditarEmpresaDTO): Promise<EmpresaDTO> => {
        const res = await api.put<EmpresaDTO>(`/empresas/${idEmpresa}`, data);
        return res.data;
    },

    eliminar: async (idEmpresa: number): Promise<void> => {
        await api.delete(`/empresas/${idEmpresa}`);
    },
    
    asignarEvento: async (idEmpresa: number, idEvento: number): Promise<void> => {
        await api.post(`/empresas/${idEmpresa}/asignar/${idEvento}`);
    },

    retirarEvento: async (idEmpresa: number, idEvento: number): Promise<void> => {
        await api.delete(`/empresas/${idEmpresa}/retirar/${idEvento}`);
    },
};

//añadir en back GET obtenerTodos y obtenerPorId