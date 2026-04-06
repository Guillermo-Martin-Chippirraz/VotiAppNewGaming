import type { UsuarioDTO } from "../types/usuario";

export interface AuthState {
    token: string | null;
    user: UsuarioDTO | null;
    loading: boolean;
}

export type AuthAction =
    | { type: "LOGIN_SUCCESS"; payload: { token: string; user: UsuarioDTO } }
    | { type: "LOGOUT" }
    | { type: "SET_LOADING"; payload: boolean };

export const authInitialState: AuthState = {
    token: localStorage.getItem("token"),
    user: null,
    loading: false,
};

export function authReducer(
    state: AuthState,
    action: AuthAction
): AuthState {
    switch (action.type) {
        case "SET_LOADING":
            return { ...state, loading: action.payload };

        case "LOGIN_SUCCESS":
            return {
                ...state,
                token: action.payload.token,
                user: action.payload.user,
                loading: false,
            };

        case "LOGOUT":
            return {
                token: null,
                user: null,
                loading: false,
            };

        default:
            return state;
    }
}