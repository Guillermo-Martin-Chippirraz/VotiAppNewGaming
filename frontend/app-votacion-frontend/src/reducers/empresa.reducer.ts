import type { EmpresaDTO } from "../types/empresa";

export interface EmpresaState {
    empresas: EmpresaDTO[];
    cargando: boolean;
}

export type EmpresaAction =
    | { type: "SET_LOADING"; payload: boolean }
    | { type: "SET_EMPRESAS"; payload: EmpresaDTO[] };

export const empresaInitialState: EmpresaState = {
    empresas: [],
    cargando: false,
};

export function empresaReducer(
    state: EmpresaState,
    action: EmpresaAction
): EmpresaState {
    switch (action.type) {
        case "SET_LOADING":
            return { ...state, cargando: action.payload };
        
        case "SET_EMPRESAS":
            return { ...state, empresas: action.payload, cargando: false };

        default:
            return state;
    }
}