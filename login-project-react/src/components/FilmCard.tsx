import "./Body.css"

import { Card, Button, Popconfirm, Spin, Input, InputNumber, Select, Flex } from "antd";
import { DeleteOutlined, EditOutlined, InboxOutlined } from "@ant-design/icons";
import Dragger from "antd/es/upload/Dragger";
import { Film, Genre } from "../service/FilmsService";

interface FilmCardProps {
  film: Film;
  genres: Genre[];
  isEditing: boolean;
  imgLoading: boolean;
  isAdmin: boolean;

  setImgLoading: (v: boolean) => void;
  onEdit: () => void;
  onDelete: () => void;
  onCancelEdit: () => void;
  onSaveEdit: () => void;

  editedFilmData: Partial<Film>;
  setEditedFilmData: React.Dispatch<React.SetStateAction<Partial<Film>>>;
}

export const FilmCard = ({
  film,
  genres,
  isEditing,
  imgLoading,
  isAdmin,
  setImgLoading,
  onEdit,
  onDelete,
  onCancelEdit,
  onSaveEdit,
  editedFilmData,
  setEditedFilmData,
}: FilmCardProps) => {
  return (
    <Card
      cover={
        isEditing ? null : (
          <div style={{ position: "relative", width: "100%", height: 375 }}>
            {imgLoading && (
              <div
                style={{
                  position: "absolute",
                  inset: 0,
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                  background: "#f0f0f0",
                }}
              >
                <Spin />
              </div>
            )}
            <img
              draggable={false}
              alt={film.title}
              src={`http://localhost:8080/film/cover/${film.coverSrc}`}
              style={{ width: "100%", height: "100%", objectFit: "cover" }}
              onLoad={() => setImgLoading(false)}
            />
          </div>
        )
      }
      hoverable
      variant="borderless"
      style={{ 
            maxWidth: 260, 
            height: "100%",
            display: "flex",
            flexDirection: "column",
        }}
      actions={
        isAdmin
          ? [
              <EditOutlined key="edit" onClick={onEdit} />,
              <Popconfirm
                key="delete"
                title="Delete Film"
                description="Are you sure to delete this film?"
                okText="Yes"
                cancelText="No"
                onConfirm={onDelete}
              >
                <DeleteOutlined />
              </Popconfirm>,
            ]
          : []
      }
    >
      {isEditing ? (
        <>
            <span style={{ fontSize: 12 }}>Cover</span>
            <Dragger
                beforeUpload={(file) => {
                setEditedFilmData((p) => ({ ...p, cover: file }));
                return false;
                }}
                style={{maxHeight:130}}
                multiple={false}
                showUploadList={{showRemoveIcon:true}}
                accept=".jpg,.jpeg"
                onRemove={() =>
                setEditedFilmData((p) => ({ ...p, cover: undefined }))
                }
            >
                <InboxOutlined />
                <p>Click or drag</p>
            </Dragger>

            <span style={{fontSize:"12px"}}>Title:</span>
            <Input
                value={editedFilmData.title || ""}
                onChange={(e) =>
                setEditedFilmData((p) => ({ ...p, title: e.target.value }))
                }
                placeholder="Title"
                style={{marginBottom: 8}}
            />

            <span style={{fontSize:"12px"}}>Description:</span>
            <Input.TextArea
                value={editedFilmData.description || ""}
                onChange={(e) =>
                setEditedFilmData((p) => ({
                    ...p,
                    description: e.target.value,
                }))
                }
                placeholder="Description"
                style={{ 
                    marginTop: 8, 
                    width: "100%",
                    height: "100px",
                    resize: "none",
                    overflowY: "auto",
                }}
            />

            <span style={{fontSize:"12px"}}>Genres</span>
            <Select
                mode="multiple"
                value={editedFilmData.genres?.map((g) => g.id)}
                onChange={(ids: number[]) =>
                setEditedFilmData((p) => ({
                    ...p,
                    genres: genres.filter((g) => ids.includes(g.id)),
                }))
                }
                style={{ width: "100%", marginBottom: 8 }}
                placeholder="Select genres"
            >
                {genres.map((g) => (
                <Select.Option key={g.id} value={g.id}>
                    {g.name}
                </Select.Option>
                ))}
            </Select>

            <span style={{fontSize:"12px"}}>Duration (min):</span>
            <InputNumber
                value={editedFilmData.duration || 0}
                onChange={(v) =>
                setEditedFilmData((p) => ({ ...p, duration: v || 0 }))
                }
                placeholder={film.duration.toString()}
                style={{ width: "100%", marginBottom: 8 }}
            />

          <Flex gap={8} style={{ marginTop: 8 }}>
            <Button type="primary" onClick={onSaveEdit}>
              Save
            </Button>
            <Button onClick={onCancelEdit}>Cancel</Button>
          </Flex>
        </>
      ) : (
        <Card.Meta
          title={film.title}
          description={
            <>
              <p
                style={{
                    display:"-webkit-box",
                    WebkitLineClamp: 3,
                    overflow: "hidden",
                    textOverflow: "ellipsis",
                    WebkitBoxOrient: "vertical",
                }}
              >{film.description}</p>
              {film.genres.map((g) => (
                <span key={g.id} style={{ fontSize: 12, marginRight: 4, color: "#888"}}>
                  {g.name}
                </span>
              ))}
              <br />
              <span>{film.duration} min</span>
            </>
          }
        />
      )}
    </Card>
  );
};
