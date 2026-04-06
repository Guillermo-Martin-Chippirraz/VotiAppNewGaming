export interface EditarVotacionDTO {
    puntuacionTotal?: number;
    respuestasJson?: string;
}

export interface VotacionDTO {
    id: number;
    idJurado: number;
    idEmpresa: number;
    idEvento: number;
    puntuacionTotal: number;
}

export interface CrearVotacionDTO {
    idEmpresa: number;
    idFormulario: number;
    idEvento: number;
    puntuacionTotal: number;
    respuestasJson: string;
}