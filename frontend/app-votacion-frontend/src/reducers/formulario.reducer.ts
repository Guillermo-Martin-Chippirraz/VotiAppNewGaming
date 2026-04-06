import type { FormularioDTO } from "../types/formulario";

export interface FormularioState {
    formularios: FormularioDTO[];
    cargando: boolean;
}

export type FormularioAction =
    | { type: "SET_LOADING"; payload: boolean }
    | { type: "SET_FORMULARIOS"; payload: FormularioDTO[] };

export const formularioInitialState: FormularioState = {
    formularios: [],
    cargando: false,
};

export function formularioReducer(
    state: FormularioState,
    action: FormularioAction
): FormularioState {
    switch (action.type) {
        case "SET_LOADING":
            return { ...state, cargando: action.payload };

        case "SET_FORMULARIOS":
            return { ...state, formularios: action.payload, cargando: false };

        default:
            return state;
    }
}