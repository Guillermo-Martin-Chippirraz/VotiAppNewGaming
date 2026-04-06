import type { VotacionDTO } from "../types/votacion";

export interface VotacionState {
    votaciones: VotacionDTO[];
    cargando: boolean;
}

export type VotacionAction =
    | { type: "SET_LOADING"; payload: boolean }
    | { type: "SET_VOTACIONES"; payload: VotacionDTO[] };

export const votacionInitialState: VotacionState = {
    votaciones: [],
    cargando: false,
};

export function votacionReducer(
    state: VotacionState,
    action: VotacionAction
): VotacionState {
    switch (action.type) {
        case "SET_LOADING":
            return { ...state, cargando: action.payload };

        case "SET_VOTACIONES":
            return { ...state, votaciones: action.payload, cargando: false };
        
        default:
            return state;
    }
}