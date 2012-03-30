package com.megaloworks.lib.GeomTools.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.megaloworks.lib.GeomTools.model.Elements;
import com.megaloworks.lib.GeomTools.model.MQOContainer;
import com.megaloworks.lib.GeomTools.model.MQOMaterial;
import com.megaloworks.lib.GeomTools.model.MQOObject;
import com.megaloworks.lib.GeomTools.model.Vertices;
import com.megaloworks.lib.GeomTools.util.Commons;
import com.megaloworks.lib.GeomTools.util.LoggingAdaptor;

public final class MQOReader {

	private static final class MQOHeaderChunk {
		public static final String LINE_1ST = "Metasequoia\\s*Document";
		public static final String LINE_2ND = "Format\\s*(\\w)+\\s*Ver\\s*(\\d*\\.\\d*)";
	}

	private static final class MQOMaterialChunk {
		public static final String BEGIN_LINE = "Material\\s*(\\d)+\\s*\\{";
		public static final String DATA_LINE = ""
				+ "(\"(\\w|\\p{InKatakana}|\\p{InHiragana}|\\p{InCJKUnifiedIdeographs})+\")\\s*"
				+ "(shader\\(\\d+\\))?\\s*"
				+ "(vcol\\(\\d+\\))?\\s*"
				+ "(col\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\))?\\s*"
				+ "(dif\\((\\d*\\.\\d*)\\))?\\s*"
				+ "(amb\\((\\d*\\.\\d*)\\))?\\s*"
				+ "(emi\\((\\d*\\.\\d*)\\))?\\s*"
				+ "(spc\\((\\d*\\.\\d*)\\))?\\s*"
				+ "(power\\((\\d*\\.\\d*)\\))?\\s*"
				+ "(tex\\(\"(\\w|\\.)+\"\\))?\\s*"
				+ "(alpha\\(\"(\\w|\\.)+\"\\))?\\s*"
				+ "(bump\\(\"(\\w|\\.)+\"\\))?\\s*"
				+ "(proj_type\\(\\d+\\))?\\s*"
				+ "(proj_pos\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\))?\\s*"
				+ "(proj_scale\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\))?\\s*"
				+ "(proj_angle\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\))?";
		public static final String MATERIAL_NAME_TOKEN = "\\s*\"\\w+";
		public static final String SHADER_TOKEN = "shader\\(\\d+";
		public static final String VCOL_TOKEN = "vcol\\(\\d+";
		public static final String COL_TOKEN = "col\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)";
		public static final String DIF_TOKEN = "dif\\((\\d*\\.\\d*)";
		public static final String AMB_TOKEN = "amb\\((\\d*\\.\\d*)";
		public static final String EMI_TOKEN = "emi\\((\\d*\\.\\d*)";
		public static final String SPC_TOKEN = "spc\\((\\d*\\.\\d*)";
		public static final String POWER_TOKEN = "power\\((\\d*\\.\\d*)";
		public static final String TEX_TOKEN = "tex\\(\"(\\w|\\.)+";
		public static final String APLANE_TOKEN = "alpha\\(\"(\\w|\\.)+";
		public static final String BUMP_TOKEN = "bump\\(\"(\\w|\\.)+";
		public static final String PROJ_TYPE_TOKEN = "proj_type\\(\\d+";
		public static final String PROJ_POS_TOKEN = "proj_pos\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)";
		public static final String PROJ_SCALE_TOKEN = "proj_scale\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)";
		public static final String PROJ_ANGLE_TOKEN = "proj_angle\\((\\d*\\.\\d*)\\s*(\\d*\\.\\d*)\\s*(\\d*\\.\\d*)";
		public static final String END_LINE = "\\}";
	}

	private static final class MQOObjectChunk {
		public static final String BEGIN_LINE = "Object\\s*(\\w|\\s|\\p{Punct}|\\p{InKatakana}|\\p{InHiragana}|\\p{InCJKUnifiedIdeographs})+\\s*\\{";
		public static final String DEPTH_LINE = "depth\\s*(\\d)+";
		public static final String FOLDING_LINE = "folding\\s*(\\d)+";
		public static final String SCALE_LINE = "scale\\s*((\\.|\\d)+(\\s)*){3}";
		public static final String ROTATION_LINE = "rotation\\s*((\\.|\\d)+(\\s)*){3}";
		public static final String TRANSLATION_LINE = "translation\\s*((\\.|\\d)+(\\s)*){3}";
		public static final String PATCH_LINE = "patch\\s*(0|1|2|3)";
		public static final String SEGMENT_LINE = "segment\\s*(1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16)";
		public static final String VISIBLE_LINE = "visible\\s*(0|15)";
		public static final String LOCKING_LINE = "locking\\s*(0|1)";
		public static final String SHADING_LINE = "shading\\s*(0|1)";
		public static final String FACET_LINE = "facet\\s*(\\.|\\d)+";
		public static final String COLOR_LINE = "color\\s*((\\.|\\d)+(\\s)*){3}";
		public static final String COLOR_TYPE_LINE = "color_type\\s*(0|1)";
		public static final String MIRROR_LINE = "mirror\\s*(0|1|2)";
		public static final String MIRROR_AXIS_LINE = "mirror_axis\\s*(1|2|4)";
		public static final String MIRROR_DIS_LINE = "mirror_dis\\s*(\\d)";
		public static final String LATHE_LINE = "";
		public static final String LATHE_AXIS_LINE = "";
		public static final String LATHE_SEG_LINE = "";
		public static final String VERTEX_BEGIN_LINE = "vertex\\s*(\\d)+\\s*\\{";
		public static final String BVERTEX_BEGIN_LINE = "BVertex\\s*(\\d)+\\s*\\{";
		public static final String FACE_BEGIN_LINE = "face\\s*(\\d)+\\s*\\{";
		public static final String FACE_DATA_LINE = "(\\d)?\\s*(V\\(((\\-)?\\d|\\s)*\\))?\\s*(M\\(\\d*\\))?\\s*(UV\\(((\\-)?\\d*\\.\\d*\\s*)*\\))?\\s*(COL\\(.*\\))?\\s*";
		public static final String FACE_NVERTEX_TOKEN = "\\d*";
		public static final String FACE_MINDEX_TOKEN = "M\\(\\d*";
		public static final String FACE_VINDEX_TOKEN = "V\\((\\d*\\s*)+";
		public static final String FACE_UV_TOKEN = "UV\\((\\d*\\.\\d*\\s*)+";
		public static final String FACE_COL_TOKEN = "COL\\(\\d*";
		public static final String END_LINE = "\\}";
	}

	private static String TAG;
	static {
		TAG = Commons.getTAG(MQOReader.class);
	}

	private static LoggingAdaptor log;

	public static void setLoggingAdaptor(LoggingAdaptor log) {
		MQOReader.log = log;
	}

	// public static MQOContainer read(Context context, String filename) {
	// MQOContainer.Builder builder = new MQOContainer.Builder();
	// AssetManager astmgr = context.getAssets();
	// try {
	// InputStreamReader isr = new InputStreamReader(astmgr.open(filename));
	// return read(isr, builder);
	// } catch (IOException e) {
	// // Log.v(TAG, "### IOException!");
	// e.printStackTrace();
	// return null;
	// }
	// }

	public static MQOContainer read(LoggingAdaptor log, InputStreamReader isr,
			MQOContainer.Builder mqoBuilder) throws FileNotFoundException,
			IOException {
		setLoggingAdaptor(log);
		return readBody(isr, mqoBuilder);
	}

	private static MQOContainer readBody(InputStreamReader isr,
			MQOContainer.Builder mqoBuilder) throws FileNotFoundException,
			IOException {
		MQOMaterial[] mqoMaterials = null;
		ArrayList<MQOObject> mqoObjects = new ArrayList<MQOObject>();

		BufferedReader br = new TrimBufferedReader(isr);

		String currentLine = br.readLine();

		// MAINLOOP
		MAIN_LOOP: do {

			// 1st stage: Header.
			if (currentLine.matches(MQOHeaderChunk.LINE_1ST)) {
				// Log.v(TAG, "### Read Header Section...");
				String line1st = new String(currentLine);
				String line2nd = br.readLine();
				String[] line2ndTokens = line2nd.split("\\s");
				float version = Float
						.parseFloat(line2ndTokens[line2ndTokens.length - 1]);
				// Log.v(TAG, "### MQO File Ver: " + version);
				mqoBuilder.setVersion_(version);
			}

			// 2nd stage: IncludeXML chunk

			// 3rd stage: Scene chunk

			// 4th stage: BackImage chunk

			// 5th stage: Material chunk
			if (currentLine.matches(MQOMaterialChunk.BEGIN_LINE)) {
				// Log.v(TAG, "### Read Material Chunk...");

				String beginLine = new String(currentLine);

				String[] beginLineTokens = beginLine.split("\\s");

				if (beginLineTokens.length < 3) {
					// Log.v(TAG, "### Error at material begin line");
					continue;
				}

				int nMaterials = Integer.parseInt(beginLineTokens[1]);
				mqoMaterials = new MQOMaterial[nMaterials];

				int materialNumber = 0;
				currentLine = br.readLine();
				do {
					MQOMaterial.Builder mqoMaterialBuilder = new MQOMaterial.Builder();
					if (!currentLine.matches(MQOMaterialChunk.DATA_LINE)) {
						continue;
					}
					String[] lineTokens = currentLine
							.split("(\"\\s+|(\")?\\)\\s*)");
					for (String matItem : lineTokens) {
						// Log.v(TAG, "### " + matItem);
						if (matItem
								.matches(MQOMaterialChunk.MATERIAL_NAME_TOKEN)) {
							// Log.v(TAG, "### MATERIAL_NAME_TOKEN");
							String[] tokens = matItem.split("\"");
							mqoMaterialBuilder.setName_(tokens[1]);
						} else if (matItem
								.matches(MQOMaterialChunk.SHADER_TOKEN)) {
							// Log.v(TAG, "### SHADER_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setShader_(Integer
									.parseInt(tokens[1]));
						} else if (matItem.matches(MQOMaterialChunk.VCOL_TOKEN)) {
							// Log.v(TAG, "### VCOL_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setVcol_(Integer
									.parseInt(tokens[1]));
						} else if (matItem.matches(MQOMaterialChunk.COL_TOKEN)) {
							// Log.v(TAG, "### COL_TOKEN");
							String[] tokens = matItem.split("(\\(|\\s)");
							float[] col = new float[4];
							col[0] = Float.parseFloat(tokens[1]);
							col[1] = Float.parseFloat(tokens[2]);
							col[2] = Float.parseFloat(tokens[3]);
							col[3] = Float.parseFloat(tokens[4]);
							mqoMaterialBuilder.setCol_(col);
						} else if (matItem.matches(MQOMaterialChunk.DIF_TOKEN)) {
							// Log.v(TAG, "### DIF_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setDif_(Float
									.parseFloat(tokens[1]));
						} else if (matItem.matches(MQOMaterialChunk.AMB_TOKEN)) {
							// Log.v(TAG, "### AMB_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setAmb_(Float
									.parseFloat(tokens[1]));
						} else if (matItem.matches(MQOMaterialChunk.EMI_TOKEN)) {
							// Log.v(TAG, "### EMI_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setEmi_(Float
									.parseFloat(tokens[1]));
						} else if (matItem.matches(MQOMaterialChunk.SPC_TOKEN)) {
							// Log.v(TAG, "### SPC_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setSpc_(Float
									.parseFloat(tokens[1]));
						} else if (matItem
								.matches(MQOMaterialChunk.POWER_TOKEN)) {
							// Log.v(TAG, "### POWER_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setPower_(Float
									.parseFloat(tokens[1]));
						} else if (matItem.matches(MQOMaterialChunk.TEX_TOKEN)) {
							// Log.v(TAG, "### TEX_TOKEN");
							String[] tokens = matItem.split("\\(\"");
							mqoMaterialBuilder.setTex_(tokens[1]);
						} else if (matItem
								.matches(MQOMaterialChunk.APLANE_TOKEN)) {
							// Log.v(TAG, "### APLANE_TOKEN");
							String[] tokens = matItem.split("\\(\"");
							mqoMaterialBuilder.setAplane_(tokens[1]);
						} else if (matItem.matches(MQOMaterialChunk.BUMP_TOKEN)) {
							// Log.v(TAG, "### BUMP_TOKEN");
							String[] tokens = matItem.split("\\(\"");
							mqoMaterialBuilder.setBump_(tokens[1]);
						} else if (matItem
								.matches(MQOMaterialChunk.PROJ_TYPE_TOKEN)) {
							// Log.v(TAG, "### PROJ_TYPE_TOKEN");
							String[] tokens = matItem.split("\\(");
							mqoMaterialBuilder.setProj_type_(Integer
									.parseInt(tokens[1]));
						} else if (matItem
								.matches(MQOMaterialChunk.PROJ_POS_TOKEN)) {
							// Log.v(TAG, "### PROJ_POS_TOKEN");
							String[] tokens = matItem.split("(\\(|\\s)");
							float[] proj_pos = new float[3];
							proj_pos[0] = Float.parseFloat(tokens[1]);
							proj_pos[1] = Float.parseFloat(tokens[2]);
							proj_pos[2] = Float.parseFloat(tokens[3]);
							mqoMaterialBuilder.setProj_pos_(proj_pos);
						} else if (matItem
								.matches(MQOMaterialChunk.PROJ_SCALE_TOKEN)) {
							// Log.v(TAG, "### PROJ_SCALE_TOKEN");
							String[] tokens = matItem.split("(\\(|\\s)");
							float[] proj_scale = new float[3];
							proj_scale[0] = Float.parseFloat(tokens[1]);
							proj_scale[1] = Float.parseFloat(tokens[2]);
							proj_scale[2] = Float.parseFloat(tokens[3]);
							mqoMaterialBuilder.setProj_scale_(proj_scale);
						} else if (matItem
								.matches(MQOMaterialChunk.PROJ_ANGLE_TOKEN)) {
							// Log.v(TAG, "### PROJ_ANGLE_TOKEN");
							String[] tokens = matItem.split("(\\(|\\s)");
							float[] proj_angle = new float[3];
							proj_angle[0] = Float.parseFloat(tokens[1]);
							proj_angle[1] = Float.parseFloat(tokens[2]);
							proj_angle[2] = Float.parseFloat(tokens[3]);
							mqoMaterialBuilder.setProj_angle_(proj_angle);
						} else {
							// Log.v(TAG, "### Unknown Token: " + matItem);
						}
					}
					currentLine = br.readLine();
					mqoMaterials[materialNumber] = mqoMaterialBuilder.build();
					materialNumber++;
				} while (!currentLine.matches(MQOMaterialChunk.END_LINE));
			}
			mqoBuilder.setMqoMaterials_(mqoMaterials);

			// 6th stage: Object chunk
			if (currentLine.matches(MQOObjectChunk.BEGIN_LINE)) {
				// Log.v(TAG, "### Read Object chunk...");

				String beginLine = new String(currentLine);

				MQOObject.Builder mqoObjectBuilder = new MQOObject.Builder();
				Vertices vertices = new Vertices();
				Elements elements = new Elements();

				String[] beginLineTokens = beginLine.split("\\s");

				if (beginLineTokens.length < 3) {
					break MAIN_LOOP;
				}

				mqoObjectBuilder.setName_(beginLineTokens[1]);

				currentLine = br.readLine();

				// Log.v(TAG, "### OBJECT_CHUNK_MAIN_LOOP Begin");
				OBJECT_CHUNK_MAIN_LOOP: do {
					// 1st sub-stage: Read Object parameters.
					if (currentLine.matches(MQOObjectChunk.DEPTH_LINE)) {
						// Log.v(TAG, "### DEPTH_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setDepth_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.FOLDING_LINE)) {
						// Log.v(TAG, "### FOLDING_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setFolding_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.SCALE_LINE)) {
						// Log.v(TAG, "### SCALE_LINE");
						String[] lineTokens = currentLine.split("\\s");
						float[] scale = { Float.parseFloat(lineTokens[1]),
								Float.parseFloat(lineTokens[2]),
								Float.parseFloat(lineTokens[3]) };
						mqoObjectBuilder.setScale_(scale);
					} else if (currentLine
							.matches(MQOObjectChunk.ROTATION_LINE)) {
						// Log.v(TAG, "### ROTATION_LINE");
						String[] lineTokens = currentLine.split("\\s");
						float[] rotation = { Float.parseFloat(lineTokens[1]),
								Float.parseFloat(lineTokens[2]),
								Float.parseFloat(lineTokens[3]) };
						mqoObjectBuilder.setRotation_(rotation);
					} else if (currentLine
							.matches(MQOObjectChunk.TRANSLATION_LINE)) {
						// Log.v(TAG, "### TRANSLATION_LINE");
						String[] lineTokens = currentLine.split("\\s");
						float[] translation = {
								Float.parseFloat(lineTokens[1]),
								Float.parseFloat(lineTokens[2]),
								Float.parseFloat(lineTokens[3]) };
						mqoObjectBuilder.setTranslation_(translation);
					} else if (currentLine.matches(MQOObjectChunk.PATCH_LINE)) {
						// Log.v(TAG, "### PATCH_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setPatch_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.SEGMENT_LINE)) {
						// Log.v(TAG, "### SEGMENT_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setSegment_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.VISIBLE_LINE)) {
						// Log.v(TAG, "### VISIBLE_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setVisible_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.LOCKING_LINE)) {
						// Log.v(TAG, "### LOCKING_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setLocking_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.SHADING_LINE)) {
						// Log.v(TAG, "### SHADING_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setShading_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.FACET_LINE)) {
						// Log.v(TAG, "### FACET_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setFacet_(Float
								.parseFloat(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.COLOR_LINE)) {
						// Log.v(TAG, "### COLOR_LINE");
						String[] lineTokens = currentLine.split("\\s");
						float[] color = { Float.parseFloat(lineTokens[1]),
								Float.parseFloat(lineTokens[2]),
								Float.parseFloat(lineTokens[3]) };
						mqoObjectBuilder.setColor_(color);
					} else if (currentLine
							.matches(MQOObjectChunk.COLOR_TYPE_LINE)) {
						// Log.v(TAG, "### COLOR_TYPE_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setColor_type_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine.matches(MQOObjectChunk.MIRROR_LINE)) {
						// Log.v(TAG, "### MIRROR_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setMirror_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine
							.matches(MQOObjectChunk.MIRROR_AXIS_LINE)) {
						// Log.v(TAG, "### MIRROR_AXIS_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setMirror_axis_(Integer
								.parseInt(lineTokens[1]));
					} else if (currentLine
							.matches(MQOObjectChunk.MIRROR_DIS_LINE)) {
						// Log.v(TAG, "### MIRROR_DIS_LINE");
						String[] lineTokens = currentLine.split("\\s");
						mqoObjectBuilder.setMirror_dis_(Float
								.parseFloat(lineTokens[1]));
					} else {
						// Log.v(TAG,
						// "### Unknown parameter at object chunk: \n    "
						// + currentLine);
					}

					// 2nd sub-stage: Read Vertex chunk.
					if (currentLine.matches(MQOObjectChunk.VERTEX_BEGIN_LINE)) {
						// Log.v(TAG, "### Read Vertex chunk...");
						int nNodes = 0;
						String vertexBeginLine = new String(currentLine);
						String[] vertexBeginLineTokens = vertexBeginLine
								.split("\\s");
						nNodes = Integer.parseInt(vertexBeginLineTokens[1]);
						vertices.setnVertices_(nNodes);
						float[] coords = new float[nNodes * 3];
						for (int i = 0; i < nNodes; i++) {
							currentLine = br.readLine();
							if (currentLine.equals("")) {
								i--;
								continue;
							}
							String[] lineTokens = currentLine.split("\\s");
							try {
								coords[i * 3 + 0] = Float
										.parseFloat(lineTokens[0]);
								coords[i * 3 + 1] = Float
										.parseFloat(lineTokens[1]);
								coords[i * 3 + 2] = Float
										.parseFloat(lineTokens[2]);
								// //Log.v(TAG, "### cooord: " + coords[i * 3]
								// + ", " + coords[i * 3 + 1] + ", "
								// + coords[i * 3 + 2]);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						vertices.setCoords_(coords);
						br.readLine();// read next line
					}

					// 3rd sub-stage: Read Vertexattr chunk.
					{
						// 1st sub-sub-stage: Read weit chunk.

						// 2nd sub-sub-stage: Read color chunk.
					}

					// 4th sub-stage: Read BVertex chunk.
					{
						// 1st sub-sub-stage: Read Vector chunk.

						// 2nd sub-sub-stage: Read weit chunk.

						// 3rd sub-sub-stage: Read color chunk.
					}

					// 5th sub-stage: Read Face chunk.
					if (currentLine.matches(MQOObjectChunk.FACE_BEGIN_LINE)) {
						// Log.v(TAG, "### Read Face chunk...");
						int nFaces = 0;
						String faceBeginLine = new String(currentLine);
						String[] faceBeginLineTokens = faceBeginLine
								.split("\\s");
						nFaces = Integer.parseInt(faceBeginLineTokens[1]);

						elements.setnElements_(nFaces);
						ArrayList<Short> connectivitiesAL = new ArrayList<Short>();
						int[] connectivitiesSeparationIndices = new int[nFaces];
						int[] materialIndices = new int[nFaces];
						ArrayList<Float> texCoordsAL = new ArrayList<Float>();
						ArrayList<Integer> vcolsAL = new ArrayList<Integer>();
						ArrayList<Float> normalsAL = new ArrayList<Float>();
						for (int l = 0; l < nFaces; l++) {
							currentLine = br.readLine();
							if (!currentLine
									.matches(MQOObjectChunk.FACE_DATA_LINE)) {
								break;
							}
							/** TODO 2010 12 11 koume uncorrect reg exp */
							// pop the first characeter of currentLine
							String nvertexStr = currentLine.substring(0, 1);
							int nvertex = Integer.parseInt(nvertexStr);
							elements.setPatchType_(nvertex);
							connectivitiesSeparationIndices[l] = (l == 0 ? nvertex - 1
									: connectivitiesSeparationIndices[l - 1]
											+ nvertex);
							currentLine = currentLine.substring(2);
							String[] lineTokens = currentLine
									.split("(\\)\\s*)");
							for (String faceItem : lineTokens) {
								// //Log.v(TAG, "### faceItem: " + faceItem);
								if (faceItem
										.matches(MQOObjectChunk.FACE_VINDEX_TOKEN)) {
									// //Log.v(TAG, "### FACE_VINDEX_TOKEN");
									String[] vindices = faceItem
											.split("(\\s+|V\\()");
									for (String vindex : vindices) {
										try {
											connectivitiesAL.add(Short
													.parseShort(vindex));
											// //Log.v(TAG, "### vindex:" +
											// vindex);
										} catch (NumberFormatException nfe) {
											// do nothing.
										}
									}
								} else if (faceItem
										.matches(MQOObjectChunk.FACE_MINDEX_TOKEN)) {
									// //Log.v(TAG, "### FACE_MINDEX_TOKEN");
									int matIndex = Integer.parseInt(faceItem
											.substring(2));
									materialIndices[l] = matIndex;
								} else if (faceItem
										.matches(MQOObjectChunk.FACE_UV_TOKEN)) {
									// //Log.v(TAG, "### FACE_UV_TOKEN");
									String[] uvCoords = faceItem
											.split("(\\s+|UV\\()");
									for (String uvCoord : uvCoords) {
										try {
											texCoordsAL.add(Float
													.parseFloat(uvCoord));
											// //Log.v(TAG, "### uvCoord: "
											// + uvCoord);
										} catch (NumberFormatException nfe) {
											// do nothing.
										}
									}
								} else if (faceItem
										.matches(MQOObjectChunk.FACE_COL_TOKEN)) {
									// //Log.v(TAG, "### FACE_COL_TOKEN");
									String[] vcols = faceItem
											.split("(\\s+|COL\\()");
									for (String vcol : vcols) {
										try {
											vcolsAL.add(Integer.parseInt(vcol));
										} catch (NumberFormatException nfe) {
											// do nothing.
										}
									}
								} else {
									// Log.v(TAG,
									// "### Unknown token at face chunk:\n   "
									// + faceItem);
								}
							}
						}

						// convert connectivitiesAL to connectivies
						short[] connectivities = Commons
								.getshorts(connectivitiesAL);

						// convert texCoordsAL to texCoords
						float[] texCoords = Commons.getfloats(texCoordsAL);

						// convert vcolsAL to vcols
						int[] vcols = Commons.getints(vcolsAL);

						elements.setConnectivities_(connectivities);
						elements
								.setConnectivitiesSeparationIndices_(connectivitiesSeparationIndices);
						elements.setMaterialIndices_(materialIndices);
						elements.setTexCoords_(texCoords);
						// Log.v(TAG, "### texCoords size: " +
						// texCoords.length);
						elements.setVcolors_(vcols);

						br.readLine();// read next line
					}

				} while (!(currentLine = br.readLine())
						.matches(MQOObjectChunk.END_LINE));// OBJECT_CHUNK_MAIN_LOOP

				// rebuild vertices coordinates and calculate normals
				short[] connectivities = elements.getConnectivities_();
				float[] oldCoords = vertices.getCoords_();
				float[] newCoords = new float[connectivities.length * 3];
				for (int i = 0; i < connectivities.length; i++) {
					newCoords[i * 3 + 0] = oldCoords[connectivities[i] * 3 + 0];
					newCoords[i * 3 + 1] = oldCoords[connectivities[i] * 3 + 1];
					newCoords[i * 3 + 2] = oldCoords[connectivities[i] * 3 + 2];
				}
				vertices.setnVertices_(newCoords.length / 3);
				vertices.setCoords_(newCoords);
				// Log.v(TAG, "### vertices size: " + newCoords.length);

				float[] normals = new float[connectivities.length * 3];
				for (int i = 0; i < connectivities.length / 3; i++) {
					int target = i * 3 + 0;
					int first = i * 3 + 2;
					int last = i * 3 + 1;
					normals[target * 3 + 0] = newCoords[first + 1]
							* newCoords[last + 2] - newCoords[first + 2]
							* newCoords[last + 1];
					normals[target * 3 + 1] = newCoords[first + 2]
							* newCoords[last + 0] - newCoords[first + 0]
							* newCoords[last + 2];
					normals[target * 3 + 2] = newCoords[first + 0]
							* newCoords[last + 1] - newCoords[first + 1]
							* newCoords[last + 0];
					double x = normals[target * 3 + 0];
					double y = normals[target * 3 + 1];
					double z = normals[target * 3 + 2];
					double sqrt = Math.sqrt(x * x + y * y + z * z);
					normals[target * 3 + 0] = (float) (x / sqrt);
					normals[target * 3 + 1] = (float) (y / sqrt);
					normals[target * 3 + 2] = (float) (z / sqrt);

					target = i * 3 + 1;
					first = i * 3 + 0;
					last = i * 3 + 2;
					normals[target * 3 + 0] = newCoords[first + 1]
							* newCoords[last + 2] - newCoords[first + 2]
							* newCoords[last + 1];
					normals[target * 3 + 1] = newCoords[first + 2]
							* newCoords[last + 0] - newCoords[first + 0]
							* newCoords[last + 2];
					normals[target * 3 + 2] = newCoords[first + 0]
							* newCoords[last + 1] - newCoords[first + 1]
							* newCoords[last + 0];
					x = normals[target * 3 + 0];
					y = normals[target * 3 + 1];
					z = normals[target * 3 + 2];
					sqrt = Math.sqrt(x * x + y * y + z * z);
					normals[target * 3 + 0] = (float) (x / sqrt);
					normals[target * 3 + 1] = (float) (y / sqrt);
					normals[target * 3 + 2] = (float) (z / sqrt);

					target = i * 3 + 2;
					first = i * 3 + 1;
					last = i * 3 + 0;
					normals[target * 3 + 0] = newCoords[first + 1]
							* newCoords[last + 2] - newCoords[first + 2]
							* newCoords[last + 1];
					normals[target * 3 + 1] = newCoords[first + 2]
							* newCoords[last + 0] - newCoords[first + 0]
							* newCoords[last + 2];
					normals[target * 3 + 2] = newCoords[first + 0]
							* newCoords[last + 1] - newCoords[first + 1]
							* newCoords[last + 0];
					x = normals[target * 3 + 0];
					y = normals[target * 3 + 1];
					z = normals[target * 3 + 2];
					sqrt = Math.sqrt(x * x + y * y + z * z);
					normals[target * 3 + 0] = (float) (x / sqrt);
					normals[target * 3 + 1] = (float) (y / sqrt);
					normals[target * 3 + 2] = (float) (z / sqrt);
				}
				elements.setNormals_(normals);
				// Log.v(TAG, "### normals size: " + normals.length);

				// Build Object
				mqoObjectBuilder.setVertices_(vertices);
				mqoObjectBuilder.setElements_(elements);
				mqoObjects.add(mqoObjectBuilder.build());
			}
		} while ((currentLine = br.readLine()) != null);// MAIN_LOOP

		// Log.v(TAG, "### Set mqoObjects to mqoCOntainerBuilder.");
		mqoBuilder.setMqoObjects_(mqoObjects.toArray(new MQOObject[mqoObjects
				.size()]));

		// close bufferedReader.
		br.close();

		// order mqoObjects

		// return
		return mqoBuilder.build();
	}
}
