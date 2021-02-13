package main.model.database.dao.mysqldb;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import main.model.database.dao.DBManager;
import main.model.database.dao.MovieDAO;
import main.model.database.dto.MovieDTO;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class MysqlMovieDAO extends MovieDAO {

    MysqlMovieDAO(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected MovieDTO buildDTO(ResultSet result) throws SQLException {
        Image image = null;
        if (result.getBlob("locandina") != null)
            image = new Image(result.getBlob("locandina").getBinaryStream());
        return new MovieDTO(
                result.getInt("id"),
                result.getString("titolo"),
                result.getString("genere"),
                result.getDate("data_uscita") == null ? null : result.getDate("data_uscita").toLocalDate(),
                result.getString("regia"),
                result.getString("attori"),
                result.getString("paese"),
                result.getInt("durata"),
                result.getString("distribuzione"),
                result.getString("sceneggiatura"),
                result.getString("fotografia"),
                result.getString("montaggio"),
                result.getString("musiche"),
                result.getString("produzione"),
                result.getString("trama"),
                image
        );
    }

    @Override
    public int save(MovieDTO movie) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "inserisci_film")) {
            statement.setInt(1, movie.getIdMovie());
            statement.setString(2, movie.getTitle());
            statement.setString(3, movie.getGenre());
            statement.setDate(4, Date.valueOf(movie.getExitDate()));
            statement.setString(5, movie.getDirection());
            statement.setString(6, movie.getActors());
            statement.setString(7, movie.getCountry());
            statement.setInt(8, movie.getDuration());
            statement.setString(9, movie.getRelease());
            statement.setString(10, movie.getScreenplay());
            statement.setString(11, movie.getPhotography());
            statement.setString(12, movie.getEditing());
            statement.setString(13, movie.getMusic());
            statement.setString(14, movie.getProduction());
            statement.setString(15, movie.getStory());
            statement.setBlob(16, createBlob(movie.getPoster()));
            return executeUpdate(statement);
        }
    }

    @Override
    public List<MovieDTO> load(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_film")) {
            statement.setInt(1, (Integer) primaryKey[0]);
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public int update(MovieDTO movie) throws SQLException, IOException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "aggiorna_film")) {
            statement.setInt(1, movie.getIdMovie());
            statement.setString(2, movie.getTitle());
            statement.setString(3, movie.getGenre());
            statement.setDate(4, Date.valueOf(movie.getExitDate()));
            statement.setString(5, movie.getDirection());
            statement.setString(6, movie.getActors());
            statement.setString(7, movie.getCountry());
            statement.setInt(8, movie.getDuration());
            statement.setString(9, movie.getRelease());
            statement.setString(10, movie.getScreenplay());
            statement.setString(11, movie.getPhotography());
            statement.setString(12, movie.getEditing());
            statement.setString(13, movie.getMusic());
            statement.setString(14, movie.getProduction());
            statement.setString(15, movie.getStory());
            statement.setBlob(16, createBlob(movie.getPoster()));
            return executeUpdate(statement);
        }
    }

    @Override
    public int delete(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "cancella_film")) {
            statement.setInt(1, (Integer) primaryKey[0]);
            return executeUpdate(statement);
        }
    }

    private Blob createBlob(Image image) throws SQLException {
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] imageByte = byteArrayOutputStream.toByteArray();
            return new SerialBlob(imageByte);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
