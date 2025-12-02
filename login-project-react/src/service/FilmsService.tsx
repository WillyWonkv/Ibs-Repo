import api from "./AxiosSettings"

export interface Film{
    id:number;
    coverSrc:string;
    title:string;
    description:string;
    duration:number;
    genres:Genre[];
}

interface Genre{
    id:number;
    name:string;
}

export const handleGetAllFilms = async (): Promise<Film[]> => {
    try{
        const resp = await api.get<Film[]>("/film");
        return resp.data;
    }catch(err){
        console.error(err);
        return [];
    }
}

export const handleDeleteFilm = async (id: number): Promise<boolean> => {
    try {
        await api.delete(`/film/${id}`);
        return true;
    } catch (err) {
        console.error(err);
        return false;
    }
};

export const handleUpdateFilm = async (film: Film) => {
    try {
        await api.put(`/film/${film.id}`, film);
    } catch (err) {
        console.error(err);
    }
}
