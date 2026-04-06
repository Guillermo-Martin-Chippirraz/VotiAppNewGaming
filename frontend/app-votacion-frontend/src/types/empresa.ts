export interface EmpresaDTO {
    idEmpresa: number;
    nombre: string;
    descripcion: string;
    logoUrl: string;
}

export interface EditarEmpresaDTO {
    nombre: string;
    descripcion: string;
    logoUrl: string;
}