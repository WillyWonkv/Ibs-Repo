import api from "./AxiosSettings"

export interface Film{
    id:number;
    coverSrc:string;
    title:string;
    description:string;
    duration:number;
    genres:Genre[];
}

export interface Genre{
    id:number;
    name:string;
}

export const handleGetAllGenresService = async (): Promise<Genre[]> => {
    try{
        const resp = await api.get<Genre[]>("/genre");
        return resp.data;
    }catch(err){
        console.error(err);
        return [];
    }   
}

export const handleGetAllFilmsService = async (): Promise<Film[]> => {
    try{
        const resp = await api.get<Film[]>("/film");
        return resp.data;
    }catch(err){
        console.error(err);
        return [];
    }
}

export const handleDeleteFilmService = async (id: number): Promise<boolean> => {
    try {
        await api.delete(`/film/${id}`);
        return true;
    } catch (err) {
        console.error(err);
        return false;
    }
};

export const handleUpdateFilmService = async (film: Film) => {
    try {
        await api.put(`/film/${film.id}`, film);
    } catch (err) {
        console.error(err);
    }
}

export const handleCreateFilmService = async (film: Film) => {
    try {
        await api.post(`/film`, film);
    } catch (err) {
        console.error(err);
    }
}

