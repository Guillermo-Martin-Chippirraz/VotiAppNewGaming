export interface EditarJuradoDTO {
    email?: string;
    datosExtra?: Record<string, unknown>;
}

export interface JuradoDTO {
    id: number;
    username: string;
}

export interface CrearJuradoDTO {
    username: string;
    password: string;
    email: string;
}