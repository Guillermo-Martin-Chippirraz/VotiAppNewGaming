import type { CrearJuradoDTO, JuradoDTO } from "../types/jurado";
import api from "./api";

export const administradorService = {
    registrarJurado: async (idAdmin: number, data: CrearJuradoDTO): Promise<JuradoDTO> => {
        const res = await api.post<JuradoDTO>(`/admin/${idAdmin}/registrar-jurado`, data);
        return res.data;
    },
};