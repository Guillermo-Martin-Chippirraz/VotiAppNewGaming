import type { CrearUsuarioDTO, UsuarioDTO } from "../types/usuario";
import api from "./api";

export const usuarioService = {
    obtenerTodos: async (): Promise<UsuarioDTO[]> => {
        const res = await api.get<UsuarioDTO[]>("/usuarios")
        return res.data;
    },

    obtenerPorId: async (id: number): Promise<UsuarioDTO> => {
        const res = await api.get<UsuarioDTO>(`/usuarios/${id}`);
        return res.data;
    },

    crear: async (data: CrearUsuarioDTO): Promise<UsuarioDTO> => {
        const res = await api.post<UsuarioDTO>("/usuarios", data);
        return res.data;
    },

    obtenerMiUsuario: async (): Promise<UsuarioDTO> => {
        const res = await api.get<UsuarioDTO>("/usuarios/me");
        return res.data;
    }
};