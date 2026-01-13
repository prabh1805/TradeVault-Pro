import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8081/api';

const authApi = axios.create({
  baseURL: `${API_BASE_URL}/auth`,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add token to requests
authApi.interceptors.request.use((config) => {
  const token = localStorage.getItem('jwtToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
    console.log('[API Request] Bearer token added:', {
      url: config.url,
      method: config.method,
      token: `${token.substring(0, 20)}...${token.substring(token.length - 10)}`,
      timestamp: new Date().toISOString(),
    });
  } else {
    console.warn('[API Request] No bearer token found for:', {
      url: config.url,
      method: config.method,
      timestamp: new Date().toISOString(),
    });
  }
  return config;
});

// Log responses
authApi.interceptors.response.use(
  (response) => {
    console.log('[API Response] Success:', {
      url: response.config.url,
      status: response.status,
      timestamp: new Date().toISOString(),
    });
    return response;
  },
  (error) => {
    console.error('[API Response] Error:', {
      url: error.config?.url,
      status: error.response?.status,
      message: error.response?.data?.message || error.message,
      timestamp: new Date().toISOString(),
    });
    return Promise.reject(error);
  }
);

export const login = (email, password) => {
  return authApi.post('/login', { email, password });
};

export const register = (userName, email, password) => {
  return authApi.post('/register', { userName, email, password });
};

export default authApi;
