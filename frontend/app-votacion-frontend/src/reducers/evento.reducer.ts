import type { EventoDTO } from "../types/evento";

export interface EventoState {
    eventos: EventoDTO[];
    loading: boolean;
}

export type EventoAction = 
    | { type: "SET_LOADING"; payload: boolean }
    | { type: "SET_EVENTOS"; payload: EventoDTO[] };

export const eventoInitialState: EventoState = {
    eventos: [],
    loading: false,
};

export function eventoReducer(
    state: EventoState,
    action: EventoAction
): EventoState {
    switch (action.type) {
        case "SET_LOADING":
            return { ...state, loading: action.payload };

        case "SET_EVENTOS":
            return { ...state, eventos: action.payload, loading: false };
        
        default:
            return state;
    }
}