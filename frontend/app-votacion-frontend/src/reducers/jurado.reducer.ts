import type { JuradoDTO } from "../types/jurado";

export interface JuradoState {
    jurados: JuradoDTO[];
    cargando: boolean;
}

export type JuradoAction =
    | { type: "SET_LOADING"; payload: boolean }
    | { type: "SET_JURADOS"; payload: JuradoDTO[] };

export const juradoInitialState: JuradoState = {
    jurados: [],
    cargando: false,
};

export function juradoReducer(
    state: JuradoState,
    action: JuradoAction
): JuradoState {
    switch (action.type) {
        case "SET_LOADING":
            return { ...state, cargando: action.payload };

        case "SET_JURADOS":
            return { ...state, jurados: action.payload, cargando: false };

        default:
            return state;
    }
}