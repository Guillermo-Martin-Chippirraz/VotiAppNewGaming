export interface UsuarioDTO {
    id: number;
    username: string;
    email: string;
    rol: string;
}

export interface CrearUsuarioDTO {
    username: string;
    password: string;
    email: string;
    rol: string;
}