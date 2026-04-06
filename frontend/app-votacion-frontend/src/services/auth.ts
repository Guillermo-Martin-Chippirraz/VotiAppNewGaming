import type { LoginRequest, TokenResponse } from "../types/auth";
import api from './api';

export const authService = {
    login: async (data: LoginRequest): Promise<TokenResponse> => {
        const res = await api.post<TokenResponse>("/auth/login", data);
        return res.data;
    }
}