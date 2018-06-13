package fr.ec.producthunt.data.database;

import android.provider.BaseColumns;

/**
 * @author Mohammed Boukadir  @:mohammed.boukadir@gmail.com
 */
public final class DataBaseContract {

    public static final String DATABASE_NAME = "database";
    public static final int DATABASE_VERSION = 1;

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMM_SPA = ",";
    public static final String INTEGER_TYPE = " INTEGER";

    /**
     * Description de la table des Posts
     **/
    public static final class PostTable implements BaseColumns {

        public static final String TABLE_NAME = "post";

        public static final String ID_COLUMN = "id";
        public static final String TITLE_COLUMN = "title";
        public static final String SUBTITLE_COLUMN = "subtitle";
        public static final String IMAGE_URL_COLUMN = "imageurl";
        public static final String POST_URL_COLUMN = "postUrl";
        public static final String COMMENTS_COUNT_COLUMN = "commentscount";


        public static final String SQL_CREATE_POST_TABLE =
                "CREATE TABLE " + PostTable.TABLE_NAME + " (" +
                        PostTable.ID_COLUMN + INTEGER_TYPE + " PRIMARY KEY" + COMM_SPA +
                        PostTable.TITLE_COLUMN + TEXT_TYPE + COMM_SPA +
                        PostTable.SUBTITLE_COLUMN + TEXT_TYPE + COMM_SPA +
                        PostTable.IMAGE_URL_COLUMN + TEXT_TYPE + COMM_SPA +
                        PostTable.COMMENTS_COUNT_COLUMN + INTEGER_TYPE + COMM_SPA +
                        PostTable.POST_URL_COLUMN + TEXT_TYPE +
                        ")";

        public static final String SQL_DROP_POST_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static String[] PROJECTIONS = new String[]{
                ID_COLUMN,
                TITLE_COLUMN,
                SUBTITLE_COLUMN,
                IMAGE_URL_COLUMN,
                POST_URL_COLUMN,
                COMMENTS_COUNT_COLUMN
        };
    }

    public static final class CollectionTable implements BaseColumns {

        public static final String TABLE_NAME = "collection";

        public static final String ID_COLUMN = "id";
        public static final String TITLE_COLUMN = "title";
        public static final String NAME_COLUMN = "name";
        public static final String BACKGROUND_IMAGE_URL_COLUMN = "imageurl";
        public static final String COMMENTS_COUNT_COLUMN = "commentscount";


        public static final String SQL_CREATE_COLLECTION_TABLE =
                "CREATE TABLE " + CollectionTable.TABLE_NAME + " (" +
                        ID_COLUMN + INTEGER_TYPE + " PRIMARY KEY" + COMM_SPA +
                        TITLE_COLUMN + TEXT_TYPE + COMM_SPA +
                        NAME_COLUMN + TEXT_TYPE + COMM_SPA +
                        COMMENTS_COUNT_COLUMN + INTEGER_TYPE + COMM_SPA +
                        BACKGROUND_IMAGE_URL_COLUMN + TEXT_TYPE +
                        ")";

        public static final String SQL_DROP_COLLECTION_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static String[] PROJECTIONS = new String[]{
                ID_COLUMN,
                TITLE_COLUMN,
                NAME_COLUMN,
                BACKGROUND_IMAGE_URL_COLUMN,
                COMMENTS_COUNT_COLUMN
        };
    }

}
