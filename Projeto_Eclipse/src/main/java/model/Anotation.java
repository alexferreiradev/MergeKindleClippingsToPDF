package model;

/**
 * Encapsula informações de uma anotação extraida de um arquivo Clip.txt
 * @author Alex
 *
 */
public class Anotation {
	
	private String pdfFileName;
	private String position;
	private String author;
	private String text;
	
	public Anotation() {
	}

	public Anotation(String pdfFileName, String position, String author, String text) {
		super();
		this.pdfFileName = pdfFileName;
		this.position = position;
		this.author = author;
		this.text = text;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((pdfFileName == null) ? 0 : pdfFileName.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anotation other = (Anotation) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (pdfFileName == null) {
			if (other.pdfFileName != null)
				return false;
		} else if (!pdfFileName.equals(other.pdfFileName))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Anotation [" + (pdfFileName != null ? "pdfFileName=" + pdfFileName + ", " : "")
				+ (position != null ? "position=" + position + ", " : "")
				+ (author != null ? "author=" + author + ", " : "") + (text != null ? "text=" + text : "") + "]";
	}
	
}
